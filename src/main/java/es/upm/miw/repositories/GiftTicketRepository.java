package es.upm.miw.repositories;

import es.upm.miw.documents.GiftTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftTicketRepository extends MongoRepository<GiftTicket, String> {

}
