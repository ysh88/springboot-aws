package org.example.book.springboot.web;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.book.springboot.config.auth.LoginUser;
import org.example.book.springboot.config.auth.dto.SessionUser;
import org.example.book.springboot.service.PostsService;
import org.example.book.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

  private final PostsService postsService;
  private final HttpSession httpSession;

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postsService.findAllDesc());
    if (user != null) {
      model.addAttribute("userName", user.getName());
    }
    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "post-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postUpdate(@PathVariable Long id, Model model) {
    PostsResponseDto dto = postsService.findById(id);
    model.addAttribute("post", dto);

    return "posts-update";
  }
}
