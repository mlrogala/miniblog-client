package pl.net.rogala.miniblogclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private RestTemplate restTemplate;      //jest niewstrzykiwalny, więc trzeba go utworzyć przez wstrzykiwalny restTemplateBuilder

    @Autowired
    public MainController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    public String home(Model model){

        PostDTO[] posts = restTemplate.getForObject("http://localhost:8090/api/posts", PostDTO[].class);
        List<PostDTO> postList = Arrays.asList(posts);
        System.out.println(postList);
        model.addAttribute("posts", postList);
        return "home";
    }

    @GetMapping("/post/{id}")
    public String showSinglePost(Model model, @PathVariable (name = "id")String postId){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", postId);
        SinglePostDTO singlePostDTO = restTemplate.getForObject("http://localhost:8090/api/post/{id}", SinglePostDTO.class, params);
        model.addAttribute("singlePost", singlePostDTO);

        System.out.println(singlePostDTO);

        return "singlePost";
    }

}
