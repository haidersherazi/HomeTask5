import java.io.FileWriter;
import java.io.IOException;

public class Metadata {

	private String version;
	private String projectName;
	private String description;

	private static final String METADATA_LOG_FILE = "logs/ProjectMetaData.txt";
	
	public Metadata (String pN, String v, String d) {
    
    	this.projectName = pN;
    	this.version = v;
    	this.description = d;
    
    }

	public void saveLogs() {
		// Create log files
        LogFileManager.createLogFile("ProjectMetaData.txt", true);
        
        try (FileWriter writer = new FileWriter(METADATA_LOG_FILE, false)) {
            
        	writer.write("Project Name        : " + projectName + "\n");
        	writer.write("Project Version     : " + version + "\n");
        	writer.write("Project Description : " + description + "\n");
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.print("Project's metadata has been saved.\n");
	}
	
	
    
}
