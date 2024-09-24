package API.pojo;

public class ArticleRequest {

    String title;
    String body;
    String date;
    String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.date = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAllNewArticleData(String title, String body, String date, String image){
        setTitle(title);
        setBody(body);
        setTime(date);
        setImage(image);
    }
}
