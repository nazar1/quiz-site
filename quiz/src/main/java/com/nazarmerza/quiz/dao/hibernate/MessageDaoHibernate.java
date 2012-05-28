package com.nazarmerza.quiz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nazarmerza.quiz.dao.MessageDao;
import com.nazarmerza.quiz.domain.Message;
import com.nazarmerza.quiz.domain.Quiz;
import com.nazarmerza.quiz.domain.User;
import com.nazarmerza.quiz.domain.types.MessageType;

@Repository
public class MessageDaoHibernate  extends GenericDaoHibernate<Message, Long>
		implements MessageDao {

	public List<Message> getAllUserMessage(User user) {
		
		Criteria criteria = buildCriteria(
				Restrictions.eq("receiver.id", user.getId()),
				Order.desc("created"));
		return criteria.list();
	}

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

	public boolean friendRequestExists(Message message) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		criteria
		.add( Restrictions.eq("sender.id", message.getSender().getId()))
		.add(Restrictions.eq("receiver.id", message.getReceiver().getId()))
		.add(Restrictions.eq("messageType", MessageType.FRIEND_REQUEST));
		if( criteria.list().size() == 0){
			return false;
		}
		return true;
	}
	
	

}
