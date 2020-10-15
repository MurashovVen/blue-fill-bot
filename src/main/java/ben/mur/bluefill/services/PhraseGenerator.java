package ben.mur.bluefill.services;

import ben.mur.bluefill.database.entities.DirectorsEntity;
import ben.mur.bluefill.database.entities.QuotesEntity;
import ben.mur.bluefill.database.repositories.DirectorsRepository;
import ben.mur.bluefill.database.repositories.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
@Scope("prototype")
public class PhraseGenerator{
    private final DirectorsRepository directorsRepository;
    private final QuotesRepository quotesRepository;

    Random rnd = new Random();

    @Autowired
    public PhraseGenerator(DirectorsRepository directorsRepository, QuotesRepository quotesRepository) {
        this.directorsRepository = directorsRepository;
        this.quotesRepository = quotesRepository;
    }

    public String getAnswer(String text){
        Integer directorId = getDirectorIdFromMessage(directorsRepository.findAll(), text);

        if(directorId > 0){
            ArrayList<QuotesEntity> quotes = quotesRepository.findAllByMovies_DirectorsId(directorId);
            QuotesEntity randomQuoteEntity = quotes.get(rnd.nextInt(quotes.size()));
            return randomQuoteEntity.getQuote();
        }

        ArrayList<QuotesEntity> quotes = quotesRepository.findAll();
        QuotesEntity randomQuote = quotes.get(rnd.nextInt(quotes.size()));

        return randomQuote.getQuote();
    }

   private Integer getDirectorIdFromMessage(ArrayList<DirectorsEntity> directors, String text){
        String lowerCaseText = text.toLowerCase();

        for (DirectorsEntity director : directors){
            String lastName = director.getLastName().toLowerCase();
            if (lowerCaseText.contains(lastName)){
                return director.getId();
            }
        }

        return -1;
   }
}
