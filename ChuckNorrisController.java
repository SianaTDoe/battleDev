@RestController
@RequestMapping("/api")
public class ChuckNorrisController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/random-quote")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @PostMapping("/favorite")
    public void addFavorite(@RequestBody Quote quote) {
        quoteService.addFavorite(quote);
    }

    @GetMapping("/favorites")
    public List<Quote> getFavorites() {
        return quoteService.getFavorites();
    }

    @PostMapping("/favorites/{id}/read")
    public void markAsRead(@PathVariable String id) {
        quoteService.markAsRead(id);
    }

    @DeleteMapping("/favorites/{id}")
    public void removeFavorite(@PathVariable String id) {
        quoteService.removeFavorite(id);
    }
}
