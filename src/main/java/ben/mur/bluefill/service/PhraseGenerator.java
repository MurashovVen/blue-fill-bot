package ben.mur.bluefill.service;

import ben.mur.bluefill.model.entity.Director;
import ben.mur.bluefill.model.entity.Quote;
import ben.mur.bluefill.repository.DirectorRepository;
import ben.mur.bluefill.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class PhraseGenerator {
    private final DirectorRepository directorRepository;
    private final QuoteRepository quoteRepository;

    private final Random rnd = new Random();

    @Autowired
    public PhraseGenerator(DirectorRepository directorRepository, QuoteRepository quoteRepository) {
        this.directorRepository = directorRepository;
        this.quoteRepository = quoteRepository;
    }

    @Transactional(readOnly = true)
    public String getAnswer(String text) {
        Integer directorId = getDirectorIdFromMessage(directorRepository.findAll(), text);

        if (directorId != null) {
            ArrayList<Quote> quotes = quoteRepository.findAllByMovies_DirectorsId(directorId);
            Quote randomQuote = quotes.get(rnd.nextInt(quotes.size()));

            return randomQuote.getQuote();
        }

        ArrayList<Quote> quotes = quoteRepository.findAll();
        Quote randomQuote = quotes.get(rnd.nextInt(quotes.size()));

        return randomQuote.getQuote();
    }

    private Integer getDirectorIdFromMessage(ArrayList<Director> directors, String text) throws NoSuchElementException {
        Optional<Director> director = directors.stream().filter(d -> text.toLowerCase().contains(d.getLastName())).findFirst();

        return director.map(Director::getId).orElse(null);
    }
}
