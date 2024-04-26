package com.lesslsmore.bili.controller.user;

import com.lesslsmore.bili.common.result.Result;
import com.lesslsmore.bili.service.user.SpaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

//@Slf4j
@RequestMapping("/user")
@RestController
public class SpaceController {
    @Autowired
    public SpaceService spaceService;

    @PostMapping("/space")
    public Result postSpace(@RequestParam String mid, @RequestParam Integer pn) {
        List<String> bvids = spaceService.saveUserSpace(mid, pn);
        return Result.ok(bvids);
    }

    @PostMapping("/spaces")
    public Result postSpaces(@RequestParam String mid, @RequestParam Integer pn) throws ExecutionException, InterruptedException {
        List<String> bvids = spaceService.saveUserSpaces(mid, pn);
        return Result.ok(bvids);
    }

    @GetMapping("/space")
    public Result getSpace(@RequestParam String mid) {
        List<String> bvids = spaceService.getBvids(mid);
        return Result.ok(bvids);
    }
}
