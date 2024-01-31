package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.MessageDto;
import com.openclassrooms.chatop.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    MessageDto messageToMessageDto(Message message);

    Message messageDtoToMessage(MessageDto messageDto);

    List<MessageDto> messagesToMessagesDto(List<Message> messages);

    List<Message> messagesDtoToMessages(List<MessageDto> messagesDto);
}
