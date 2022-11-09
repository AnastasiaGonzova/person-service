package core.controller;

import core.api.service.SignalService;
import core.model.Signal;
import dto.external.SignalDto;
import dto.internal.SignalInternalDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class SignalController {

    private SignalService signalService;
    private ModelMapper modelMapper;

    @GetMapping("/signals/{signal_id}")
    public SignalDto get(@PathVariable(name = "signal_id") Long signalId){
        return Optional.of(signalId)
                .map(signalService::getAndInitialize)
                .map(current->modelMapper.map(current, SignalDto.class))
                .orElseThrow();
    }

    @PostMapping("persons/{person_data_id}/signals")
    public SignalDto create(@RequestBody SignalInternalDto signalJson, @PathVariable(name="person_data_id") Long personId){
        return Optional.ofNullable(signalJson)
                .map(current->modelMapper.map(current, Signal.class))
                .map(current->signalService.create(current, personId))
                .map(current->modelMapper.map(current, SignalDto.class))
                .orElseThrow();
    }

    @PutMapping("/signals/{signal_id}/update")
    public SignalDto update(@PathVariable(name="signal_id") Long signalId, @RequestBody SignalInternalDto signalJson){
        return Optional.ofNullable(signalJson)
                .map(current->modelMapper.map(current, Signal.class))
                .map(toUpdate->signalService.update(signalId, toUpdate))
                .map(current->modelMapper.map(current, SignalDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/persons/{person_data_id}/signals/{signal_id}/admin/delete")
    public void delete(@PathVariable(name="signal_id") Long signalId, @PathVariable(name="person_data_id") Long personDataId){
        signalService.delete(signalId, personDataId);
    }
}
