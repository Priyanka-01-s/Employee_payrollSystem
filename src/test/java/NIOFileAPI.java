import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class NIOFileAPI {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGrond";

    @Test
    public void givePathThenChecked() throws IOException{
        //check if file exits
        Path homePath = Paths.get(HOME);
        assertTrue(Files.exists(homePath));
        
        //delete file and check file not exist
        Path playPath = Paths.get(HOME+"/"+PLAY_WITH_NIO);
        if(Files.exists(playPath)) Files.delete(playPath); 
		assertTrue(Files.notExists(playPath));	
		
		//Create Directory
		Files.createDirectory(playPath);
		assertTrue(Files.exists(playPath));
		
		//Create Empty File
		IntStream.range(1, 10).forEach(cntr ->{ 
			Path tempFile=Paths.get(playPath+"/temp"+cntr);
			assertTrue(Files.notExists(tempFile));
			try {Files.createFile(tempFile);}
			catch (IOException e) { }
			assertTrue(Files.exists(tempFile));
		});
		
		//List Files,Directories as well as Files with Extension
		Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		Files.newDirectoryStream(playPath , path ->path.toFile().isFile() &&
				                                   path.toString().startsWith("temp"))
		.forEach(System.out::println);
        
    }
}
