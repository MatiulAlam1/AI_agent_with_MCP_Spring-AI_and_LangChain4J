package com.dep.aiOrderAssistant.records;
import java.util.UUID;

public record UserChatRequest(UUID sessionId, String message) {}

