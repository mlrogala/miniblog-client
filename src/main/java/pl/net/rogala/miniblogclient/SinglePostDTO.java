package pl.net.rogala.miniblogclient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class SinglePostDTO {
        private  Long id;
        private  String title;
        private String postBody;
        private LocalDateTime added;
}
