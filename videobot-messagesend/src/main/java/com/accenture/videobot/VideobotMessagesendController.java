package com.accenture.videobot;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.accenture.videobot.helper.Constants;
import com.accenture.videobot.helper.PhoneValidadeHelper;
import com.accenture.videobot.helper.VideobotMessagesendHelper;
import com.accenture.videobot.helper.WclHelper;
import com.accenture.videobot.model.LogSendMessage;
import com.accenture.videobot.model.LogUpdateLink;
import com.accenture.videobot.model.VideobotMessagesend;
import com.accenture.videobot.model.VideobotSendError;
import com.accenture.videobot.model.VideobotSendSucess;
import com.accenture.videobot.model.mem.Body;
import com.accenture.videobot.model.mem.Data;
import com.accenture.videobot.model.mem.Header;
import com.accenture.videobot.model.sendWcl.BodyWcl;
import com.accenture.videobot.model.sendWcl.Text;
import com.accenture.videobot.model.sendWcl.Wcl;
import com.accenture.videobot.repository.LogSendMessageRepository;
import com.accenture.videobot.repository.LogUpdateLinkRepository;
import com.accenture.videobot.repository.VideobotMessagesendRepository;
import com.accenture.videobot.repository.VideobotSendErrorRepository;
import com.accenture.videobot.repository.VideobotSendSucessRepository;

@RestController
@RequestMapping("/videobot")
public class VideobotMessagesendController {

	@Autowired
    Environment environment;
	
	@Autowired
	VideobotMessagesendRepository repository;

	@Autowired
	LogSendMessageRepository logSendRepository;

	@Autowired
	LogUpdateLinkRepository logUpdateRepository;
	
	@Autowired
	VideobotSendSucessRepository sendSucessRepository;
	
	@Autowired
	VideobotSendErrorRepository sendErrorRepository;

	@GetMapping("/all")
	public Iterable<VideobotMessagesend> getAll() {
		return repository.findAll();
	}
	
	@GetMapping("/teste")
	public String teste() {
		return environment.getProperty("app.environment");
	}

	@GetMapping("/deleteAll")
	public void deleteAll() {
		System.out.println("Zerando todas as bases do Mongodb");
		repository.deleteAll();
		logSendRepository.deleteAll();
		logUpdateRepository.deleteAll();
		sendSucessRepository.deleteAll();
		sendErrorRepository.deleteAll();
	}

	@GetMapping("/updateLinks")
	public void updateLinksFromCSVFile() {
		VideobotMessagesendHelper helper = new VideobotMessagesendHelper();
		String path = Constants.FILE_LINK_VIDEO;
		String delimitador = Constants.DELIMITADOR_CSV;

		for (String linha : helper.readCSV(path)) {
			String[] campos = linha.split(delimitador);
			String id = campos[0];
			String link_video = campos[1];
			try {
				if (repository.findById(id).get() != null) {
					VideobotMessagesend videobotMessagesend = repository.findById(id).get();
					videobotMessagesend.setLink(link_video);
					System.out.print("Atualizando ID: " + id);
					repository.save(videobotMessagesend);
					System.out.print(" - Concluído! \n");

					// Log Mongo Sucess
					LogUpdateLink log = new LogUpdateLink();
					log.setIdSendMessage(id);
					log.setMessage("Atualização efetuada com sucesso");
					log.setTimestamp(new Date());
					log.setType("Sucesso");
					logUpdateRepository.save(log);
				} else
					System.out.println("ID Não encontrado na base de dados");
			} catch (Exception e) {
				System.out.println("ID: " + id + " - Não encontrado na base de dados");
				// Log Mongo Erro
				LogUpdateLink log = new LogUpdateLink();
				log.setIdSendMessage(id);
				log.setMessage("Erro na atualização - " + e.toString());
				log.setTimestamp(new Date());
				log.setType("Erro");
				logUpdateRepository.save(log);
				e.printStackTrace();
				continue;
			}
		}
	}

	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/mem", method = RequestMethod.POST)
	public void sendMessage() {
		VideobotMessagesendHelper helper = new VideobotMessagesendHelper();

		Iterable<VideobotMessagesend> listSend = repository.findAll();
		for (VideobotMessagesend videobot : listSend) {
			try {

				// setar os objetos de acordo com o padrão no Novo Mem
				Data data = new Data();
				data.setContato_1(videobot.getTelefoneContato());
				data.setContato_2(videobot.getTelefoneContato());
				data.setContato_3(videobot.getTelefoneContato());
				data.setLink(videobot.getLink());
				data.setNome(videobot.getPrimeiroNome());

				Header header = new Header();

				Body body = new Body();
				body.setData(data);
				body.setHeader(header);

				// Converter para JSON
				String requestJson = helper.convertToJson(body);
				System.out.println(requestJson);

				PhoneValidadeHelper phoneValidade = new PhoneValidadeHelper();
				boolean numberIsValid;
				numberIsValid = phoneValidade.validatePhone(videobot.getTelefoneContato());
				if (numberIsValid) {
					// Colocar o header de acordo com o Padrão do Novo Mem
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.setBearerAuth(Constants.TOKEN_MEM);

					HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

					String answer = restTemplate.postForObject(Constants.API_MEM, entity, String.class);
					System.out.println(answer);

					helper.delay(Constants.DELAY_API);

					// Log Mongo Sucess
					LogSendMessage log = new LogSendMessage();
					log.setIdSendMessage(videobot.getId());
					log.setMessage(answer);
					log.setTimestamp(new Date());
					log.setType("Sucesso");
					logSendRepository.save(log);

				} else {
					// Logo Mongo de Número Inválido
					LogSendMessage log = new LogSendMessage();
					log.setIdSendMessage(videobot.getId());
					log.setMessage("Número de telefone não é válido");
					log.setTimestamp(new Date());
					log.setType("Erro");
					logSendRepository.save(log);
				}
			} catch (Exception e) {
				System.out.println("Não foi possível fazer a requisição a API do MEM");
				// Log Mongo Erro
				LogSendMessage log = new LogSendMessage();
				log.setIdSendMessage(videobot.getId());
				log.setMessage("Erro no envio da Mensagem - " + e.toString());
				log.setTimestamp(new Date());
				log.setType("Erro");
				logSendRepository.save(log);
				e.printStackTrace();
				continue;
			}
		}
		System.out.println("Limpando a Base");
		repository.deleteAll();
	}

	@RequestMapping(value = "/wcl", method = RequestMethod.POST)
	public void sendMessageWcl() {
		VideobotMessagesendHelper helper = new VideobotMessagesendHelper();
		Iterable<VideobotMessagesend> listSend = repository.findAll();

		for (VideobotMessagesend videobot : listSend) {
			try {

				// setar os objetos de acordo com o padrão Wcl
				WclHelper wclHelper = new WclHelper();
				Wcl wcl = new Wcl();
				wcl.setPhone(videobot.getTelefoneContato());

				Text text = new Text();
				text.setBody(
						"A gente fez um vídeo com várias dicas pra você aproveitar ainda mais os benefícios da sua "
								+ "Fibra da Oi! Confira agora " + videobot.getPrimeiroNome() + ":) "
								+ wclHelper.getShortURL(videobot.getLink()));

				BodyWcl bodyWcl = new BodyWcl();
				bodyWcl.setText(text);
				bodyWcl.setWcl(wcl);

				// Converter para JSON
				String requestJson = helper.convertToJson(bodyWcl);
				System.out.println(requestJson);

				// Colocar o header de acordo com o Padrão do Novo Mem
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setBearerAuth(wclHelper.getToken());
				if (wclHelper.validateContact(videobot.getTelefoneContato())) {

					HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
					String answer = restTemplate.postForObject(Constants.API_CONTACT_WCL_PROD, entity, String.class);

					System.out.println(answer);

					helper.delay(Constants.DELAY_API);

					// Log Mongo Sucess
					LogSendMessage log = new LogSendMessage();
					log.setIdSendMessage(videobot.getId());
					log.setMessage(answer);
					log.setTimestamp(new Date());
					log.setType("Sucesso");
					logSendRepository.save(log);
					
					//Guardar na Tabela de 'enviados com sucesso'
					VideobotSendSucess videobotSendSucess = new VideobotSendSucess();
					videobotSendSucess.setLink(videobot.getLink());
					videobotSendSucess.setPrimeiroNome(videobot.getPrimeiroNome());
					videobotSendSucess.setTelefoneContato(videobot.getTelefoneContato());
					sendSucessRepository.save(videobotSendSucess);
					
				}
				else {
					// Log Mongo Error
					LogSendMessage log = new LogSendMessage();
					log.setIdSendMessage(videobot.getId());
					log.setMessage("Número de celular inválido");
					log.setTimestamp(new Date());
					log.setType("Erro");
					logSendRepository.save(log);
					
					//Guardar na Tabela de 'não enviados'
					VideobotSendError videobotSendError = new VideobotSendError();
					videobotSendError.setLink(videobot.getLink());
					videobotSendError.setPrimeiroNome(videobot.getPrimeiroNome());
					videobotSendError.setTelefoneContato(videobot.getTelefoneContato());
					sendErrorRepository.save(videobotSendError);
				}
			} catch (Exception e) {
				System.out.println("Não foi possível fazer a requisição a API");
				// Log Mongo Erro
				LogSendMessage log = new LogSendMessage();
				log.setIdSendMessage(videobot.getId());
				log.setMessage("Erro no envio da Mensagem - " + e.toString());
				log.setTimestamp(new Date());
				log.setType("Erro");
				logSendRepository.save(log);
				
				//Guardar na Tabela de 'não enviados'
				VideobotSendError videobotSendError = new VideobotSendError();
				videobotSendError.setLink(videobot.getLink());
				videobotSendError.setPrimeiroNome(videobot.getPrimeiroNome());
				videobotSendError.setTelefoneContato(videobot.getTelefoneContato());
				sendErrorRepository.save(videobotSendError);
				
				e.printStackTrace();
				continue;
			}
		}
		System.out.println("Limpando a Base após extração");
		repository.deleteAll();
	}
	

}