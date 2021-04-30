package cat.tecnocampus.notes.application.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import cat.tecnocampus.notes.application.portsIn.DeleteUserNotes;
import cat.tecnocampus.notes.configuration.ReceiverDeleteUserNotesChannel;

@EnableBinding(ReceiverDeleteUserNotesChannel.class)

public class DeleteUserNotesService implements DeleteUserNotes {
	
	public final NoteUseCases noteUseCases;
	
	public DeleteUserNotesService(NoteUseCases noteUseCases) { this.noteUseCases = noteUseCases;}

	@Override
	@StreamListener("receiverDeleteNotesChannel")
	public void deleteUserNotes(CommandNotes command) {
	
		System.out.println("Message received: " + command.getUserName());
		noteUseCases.deleteUserNotes(command.getUserName());
	}
	
	
	
}
