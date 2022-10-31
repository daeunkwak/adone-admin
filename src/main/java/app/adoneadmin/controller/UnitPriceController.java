package app.adoneadmin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "unitPrices", description = "회원 API")
@RequestMapping("/unitprices")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UnitPriceController {
}
