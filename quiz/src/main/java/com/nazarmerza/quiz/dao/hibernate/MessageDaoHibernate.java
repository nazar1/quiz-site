package com.nazarmerza.quiz.dao.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nazarmerza.quiz.dao.MessageDao;
import com.nazarmerza.quiz.domain.Challenge;
import com.nazarmerza.quiz.domain.FriendRequest;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Note;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;

@Repository
public class MessageDaoHibernate  extends GenericDaoHibernate<Message, Long>
		implements MessageDao {


	public List<FriendRequest> getUserFreindRequests(User user){
		List<FriendRequest> friendRequests = new LinkedList<FriendRequest>();
		List<Message> messages = getMessagesByDiscriminator("FR_MS");
		for(Message message: messages){
			friendRequests.add((FriendRequest) message);
		}
		return friendRequests;
	}
	public List<Challenge> getUserChallenges(User user){
		List<Challenge> challenges = new LinkedList<Challenge>();
		List<Message> messages = getMessagesByDiscriminator("CH_MS");
		for(Message message: messages){
			challenges.add((Challenge) message);
		}
		return challenges;
	}
	public List<Note> getUserNotes(User user){
		List<Note> notes = new LinkedList<Note>();
		List<Message> messages = getMessagesByDiscriminator("NT_MS");
		for(Message message: messages){
			notes.add((Note) message);
		}
		return notes;
	}

	
	public boolean friendRequestExists(Message message) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		criteria
		.add( Restrictions.eq("sender.id", message.getSender().getId()))
		.add(Restrictions.eq("receiver.id", message.getReceiver().getId()))
		.add(Restrictions.eq("class", "FR_MS"));
		if( criteria.list().size() == 0){
			return false;
		}
		return true;
	}
	
	/* private methods */
	private List<Message> getMessagesByDiscriminator(String discriminator){
		Criteria criteria = buildCriteria(Restrictions.eq("class", discriminator),
				null);
		return criteria.list();
	}

	
	/* unused */
	
	public List<Message> getActiveUserMessages(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Message getFriendRequest(Message message) {
		String[] excludePropery = {"MESSAGE_ID"};
		List<Message> messages = findByExample(message, excludePropery);
		if (messages.size() == 0) {
			return null;
		}
		return messages.get(0);
	}

	
	
	

		
	public List<Message> getAllUserMessage(User user) {
		
		Criteria criteria = buildCriteria(
				Restrictions.eq("receiver.id", user.getId()),
				Order.desc("created"));
		return criteria.list();
	}
}
