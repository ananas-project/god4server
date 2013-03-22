package ananas.app.god4server.client.core;

import java.io.File;

public interface G4sClientFactory {

	G4sClient newClient(File file);

	G4sClient openClient(File file);
}
