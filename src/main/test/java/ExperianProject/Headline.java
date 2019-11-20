package ExperianProject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
class Headline{

    String sourceID;
    String author;
    String title;

    public String getSourceID() {
        return sourceID;
    }

    @JsonProperty("source")
    public void setSourceID(Map<String,Object> source) {
        this.sourceID = String.valueOf( source.get("id") );
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Headline{" +
                "sourceID='" + sourceID + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
