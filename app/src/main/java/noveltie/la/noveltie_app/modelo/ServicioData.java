package noveltie.la.noveltie_app.modelo;

public class ServicioData {
    private String imagePrimary;
    private String name;
    private String symbol;
    private String price;
    private String categoryName;
    private Promotion promotion;
    private String subcategoryName;
    private String description;


    public ServicioData(String imagePrimary, String name, String symbol, String price, String categoryName,Promotion promotion,
                        String subcategoryName, String description) {
        this.imagePrimary = imagePrimary;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.categoryName = categoryName;
        this.promotion = promotion;
        this.subcategoryName = subcategoryName;
        this.description = description;
    }

    public String getImagePrimary() {
        return imagePrimary;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public String getDescription() {
        return description;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public class Promotion {
        private boolean flag;
        private String price;

        public Promotion(boolean flag, String price) {
            this.flag = flag;
            this.price = price;
        }

        public Boolean getFlag() {
            return flag;
        }

        public String getPrice() {
            return price;
        }
    }
}
