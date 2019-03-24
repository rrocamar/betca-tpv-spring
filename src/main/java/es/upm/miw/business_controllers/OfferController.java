package es.upm.miw.business_controllers;

import es.upm.miw.documents.Offer;
import es.upm.miw.dtos.input.OfferInputDto;
import es.upm.miw.dtos.output.OfferOutputDto;
import es.upm.miw.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    /*public List<OfferOutputDto> readAll() {
        return this.offerRepository.findAll();
    }*/

    public OfferOutputDto create(OfferInputDto offerInputDto) {
        Offer offer = new Offer(Integer.toString(offerInputDto.hashCode()), offerInputDto.getOffername() , offerInputDto.getEndDate(), offerInputDto.getArticleLine());
        this.offerRepository.save(offer);
        return new OfferOutputDto(offer.getId(), offer.getOffername(), offer.getEndDate(), offer.getArticleLine());
    }

    public void delete(String offerId) {
        Optional<Offer> offer = this.offerRepository.findById(offerId);
        if (offer.isPresent()) {
            this.offerRepository.delete(offer.get());
        }
    }
}