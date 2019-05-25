package app.saikat.NotifyServer.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.saikat.NotifyServer.WebsocketConfigurations;
import app.saikat.NotifyServer.models.Device;
import app.saikat.NotifyServer.services.DeviceService;

@RestController
public class SocketController {

    @Autowired
    private DeviceService service;

    @Autowired
    private WebsocketConfigurations configurations;

    @PostMapping(name = "/addDevice")
    public ResponseEntity<Device> addDevice(@RequestBody ReqBody reqBody) {
        if (configurations.isAddDeviceEnabled()) {
            Device device = service.addDevice(reqBody.name);
            return ResponseEntity.status(HttpStatus.OK).body(device);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

}