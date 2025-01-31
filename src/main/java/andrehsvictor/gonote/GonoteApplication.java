package andrehsvictor.gonote;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class GonoteApplication {

	private static final String OS = System.getProperty("os.name").toLowerCase();

	private static final String getPermissionCommand = "chmod +x rsa.sh";

	private static final String executeCommand = "./rsa.sh";

	public static void main(String[] args) throws IOException {
		if (!OS.contains("win")) {
			ProcessBuilder getPermission = new ProcessBuilder(getPermissionCommand);
			ProcessBuilder execute = new ProcessBuilder(executeCommand);
			getPermission.start();
			execute.start();
		} else {
			log.warn("Automatic RSA key generation is not supported on Windows. Please run the rsa.sh script manually.");
		}
		SpringApplication.run(GonoteApplication.class, args);
	}

}
