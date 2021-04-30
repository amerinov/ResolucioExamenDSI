package DeleteUserNotesAdapter;



import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import cat.tecnocampus.users.application.portsOut.DeleteUserNotes;
import cat.tecnocampus.users.configuration.DeleteUserNotesChannel;

@Component
@EnableBinding(DeleteUserNotesChannel.class)
public class DeleteUserNotesAdapter  implements DeleteUserNotes{
	private MessageChannel deleteUserNotesChannel;

	public DeleteUserNotesAdapter(DeleteUserNotesChannel channels) {
		this.deleteUserNotesChannel = channels.senderDeleteNotesChannel();
	}
	
		
	@Override
	public void deleteUsersNotes(String userName) {
		// TODO Auto-generated method stub
		CommandNotes command = new CommandNotes(userName);
		deleteUserNotesChannel.send(MessageBuilder.withPayload(command).build());
		
	}
	

}
