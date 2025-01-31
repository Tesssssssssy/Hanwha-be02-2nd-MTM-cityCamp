package com.example.campingontop.house.controller;

import com.example.campingontop.house.model.House;
import com.example.campingontop.house.model.request.GetHouseListPagingDtoReq;
import com.example.campingontop.house.model.request.PostCreateHouseDtoReq;
import com.example.campingontop.house.model.request.PutUpdateHouseDtoReq;
import com.example.campingontop.house.model.response.GetFindHouseDtoRes;
import com.example.campingontop.house.model.response.PutUpdateHouseDtoRes;
import com.example.campingontop.house.service.HouseService;
import com.example.campingontop.user.model.User;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Tag(name="House", description = "House 숙소 CRUD")
@Api(tags = "House")
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService houseService;

    @Operation(summary = "House 숙소 등록",
            description = "숙소 등록를 등록하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @PostMapping("/create")
    public ResponseEntity createHouse(
            @AuthenticationPrincipal User user,
            @RequestPart PostCreateHouseDtoReq postCreateHouseDtoReq,
            @RequestPart MultipartFile[] uploadFiles
            ) {
        return ResponseEntity.ok().body(houseService.createHouse(user, postCreateHouseDtoReq, uploadFiles));
    }
    @Operation(summary = "House 가격 내림차순 조회",
            description = "가격 내림차순으로 숙소를 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/pricedesc")
    public ResponseEntity findHouseByPriceDesc(GetHouseListPagingDtoReq req) {
        return ResponseEntity.ok().body(houseService.findByPriceDesc(req));
    }

    @Operation(summary = "House 가격 오름차순 조회",
            description = "가격 오름차순으로 숙소를 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/priceasc")
    public ResponseEntity findHouseByPriceAsc(GetHouseListPagingDtoReq req) {
        return ResponseEntity.ok().body(houseService.findByPriceAsc(req));
    }
    @Operation(summary = "House 이름으로 조회",
            description = "숙소 이름으로 숙소를 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/name")
    public ResponseEntity findHouseByName(GetHouseListPagingDtoReq req, String name) {
        return ResponseEntity.ok().body(houseService.findByName(req, name));
    }
  
    @Operation(summary = "House 주소로 조회",
            description = "숙소 주소로 숙소를 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/address")

    public ResponseEntity findHouseByaddress(GetHouseListPagingDtoReq req, String address) {
        return ResponseEntity.ok().body(houseService.findByAddress(req, address));
    }
 
  
  
    @Operation(summary = "House 거리순으로 조회",
            description = "사용자에서 가까운 위치 순으로 숙소를 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/location")
    public ResponseEntity getNearestHouseList(GetHouseListPagingDtoReq req, Double latitude, Double longitude) {
        return ResponseEntity.ok().body(houseService.getNearestHouseList(req, latitude,longitude));
    }


    @Operation(summary = "House 숙소 조회",
            description = "숙소 ID로 숙소 1개를 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/{houseId}")
    public ResponseEntity findHouseById(@Parameter(description = "조회할 house의 id") @PathVariable Long houseId) {
        return ResponseEntity.ok().body(houseService.findHouseById(houseId));
    }


    @Operation(summary = "House 숙소 목록 조회",
            description = "전체 숙소들의 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/list")
    public ResponseEntity findHouseList(GetHouseListPagingDtoReq req) {
        List<GetFindHouseDtoRes> houseList = houseService.findHouseList(req);
        return ResponseEntity.ok().body(houseList);
    }


    /*
    public ResponseEntity getHousesWithinDistance(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude
    ) {
        List<House> houses = houseService.findHousesWithinDistance(latitude, longitude);
        return ResponseEntity.ok(houses);
    }
    */



    @Operation(summary = "House 숙소 정보 수정",
            description = "숙소의 정보를 수정하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @PutMapping("/update/{houseId}")
    public ResponseEntity updateHouse(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PutUpdateHouseDtoReq putUpdateHouseDtoReq,
            @PathVariable Long houseId
    ) {
        PutUpdateHouseDtoRes house = houseService.updateHouse(user, putUpdateHouseDtoReq, houseId);
        return ResponseEntity.ok().body(house);
    }


    @Operation(summary = "House 숙소 좋아요 +1",
            description = "숙소의 좋아요를 +1 하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping(value = "/like/{houseId}")
    public ResponseEntity addHeartHouse(
            @AuthenticationPrincipal User user,
            @PathVariable Long houseId
    ) {
        return ResponseEntity.ok().body(houseService.addHeartHouse(user, houseId));
    }



    @Operation(summary = "House 숙소 삭제",
            description = "숙소 ID로 숙소 데이터 1개를 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @PutMapping("/delete/{houseId}")
    public ResponseEntity deleteHouse(
            @AuthenticationPrincipal User user,
            @Parameter(description = "삭제할 house의 id") @PathVariable Long houseId
    ) {
        houseService.deleteHouse(user, houseId);
        return ResponseEntity.ok().body("House delete success");
    }
}
