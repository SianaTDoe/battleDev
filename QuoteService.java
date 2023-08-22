@Service
public class QuoteService {

    private List<Quote> favoriteQuotes = new ArrayList<>();

    public Quote getRandomQuote() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.chucknorris.io/jokes/random";
        ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);
        Quote quote = new Quote();
        quote.setId((String) response.getBody().get("id"));
        quote.setContent((String) response.getBody().get("value"));
        return quote;
    }

    public void addFavorite(Quote quote) {
        favoriteQuotes.add(quote);
    }

    public List<Quote> getFavorites() {
        return favoriteQuotes;
    }

    public void markAsRead(String id) {
        for (Quote quote : favoriteQuotes) {
            if (quote.getId().equals(id)) {
                quote.setRead(true);
                break;
            }
        }
    }

    public void removeFavorite(String id) {
        favoriteQuotes.removeIf(quote -> quote.getId().equals(id));
    }
}
