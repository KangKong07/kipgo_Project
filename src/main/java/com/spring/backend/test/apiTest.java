package com.spring.backend.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Test API Controller
 */
@RestController
@Slf4j
@RequestMapping("/kipgo-api/test/1.0")
public class apiTest {

    @GetMapping("/api-call")
    public ResponseEntity getApiCallTest (
            @RequestParam Map<String, Object> xParam ) {

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("testCode", xParam.get("testCode").toString());
        resultMap.put("result", "success");

        return ResponseEntity.ok(resultMap);
    }
}
