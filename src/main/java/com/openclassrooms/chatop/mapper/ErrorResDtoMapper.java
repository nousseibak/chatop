package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.ErrorResDto;
import com.openclassrooms.chatop.model.ErrorRes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ErrorResDtoMapper {

    ErrorResDto ErrorResToErrorResDto(ErrorRes errorRes);

    ErrorRes ErrorResDtoToErrorRes(ErrorResDto errorResInDto);

    List<ErrorResDto> ErrorResToErrorResDto(List<ErrorRes> errorRes);

    List<ErrorRes> ErrorResDtoToErrorRes(List<ErrorResDto> errorResDto);
}
