package com.example.websocketserver.controller;

import com.example.websocketserver.model.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MensajeController {

    @MessageMapping("/envio")
    @SendTo("/tema/mensajes")
    public Mensaje envio(Mensaje mensaje) {
        return new Mensaje(mensaje.nombre(),mensaje.contenido());

    }
}
