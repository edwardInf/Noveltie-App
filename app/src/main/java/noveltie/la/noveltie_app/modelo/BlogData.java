package noveltie.la.noveltie_app.modelo;

public class BlogData {
    private int id;
    private String title;
    private String content;
    private String date;
    private String image_xlarge_460;
    private String image_large_360;
    private String image_land_280;
    private String link_blog;


    public BlogData(int id, String title, String content, String date, String image_xlarge_460,
                    String image_large_360, String image_land_280, String link_blog) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.image_xlarge_460 = image_xlarge_460;
        this.image_large_360 = image_large_360;
        this.image_land_280 = image_land_280;
        this.link_blog = link_blog;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImage_xlarge_460() {
        return image_xlarge_460;
    }

    public String getImage_large_360() {
        return image_large_360;
    }

    public String getImage_land_280() {
        return image_land_280;
    }

    public String getDate() {
        return date;
    }

    public String getLink_blog() {
        return link_blog;
    }
}
