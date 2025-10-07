package com.example.fakedata.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import com.example.fakedata.api.ApiResponse;
import com.example.fakedata.dto.UserDto;
import com.example.fakedata.mapper.UserMapper;
import com.example.fakedata.service.DataGenService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Users", description = "Fake user generation endpoints using Datafaker + EasyRandom")
public class UserController
{
   private final DataGenService service;

   public UserController(DataGenService service)
   {
      this.service = service;
   }

   @GET
   @Path("/{count}")
   @Operation(summary = "Generate N users (Datafaker by default)")
   public ApiResponse<List<UserDto>> generateUsers(
         @PathParam("count") int count, @QueryParam("easy") boolean easy)
   {
      return ApiResponse.of(
            service.manyUsers(count, easy).stream().map(UserMapper::toDto).collect(Collectors.toList()));
   }

   @GET
   @Path("/one")
   @Operation(summary = "Get a single user (Datafaker)")
   public ApiResponse<UserDto> one()
   {
      return ApiResponse.of(UserMapper.toDto(service.randomUserViaDatafaker()));
   }

   @POST
   @Path("/create/{count}")
   @Operation(summary = "POST to create N users (simulated). GET alternative provided.")
   public ApiResponse<List<UserDto>> createUsersPost(@PathParam("count") int count)
   {
      return ApiResponse.of(
            service.manyUsers(count, false).stream().map(UserMapper::toDto).collect(Collectors.toList()));
   }

   @GET
   @Path("/create/{count}")
   @Operation(summary = "GET alternative to POST create N users (simulated)")
   public ApiResponse<List<UserDto>> createUsersGet(@PathParam("count") int count)
   {
      return createUsersPost(count);
   }
}
