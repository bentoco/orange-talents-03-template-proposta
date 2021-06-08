package br.com.zup.proposal.card.travel;

import br.com.zup.proposal.card.Card;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelNoticeRequest {

    @NotBlank
    private String destiny;

    @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate travelEndTime;

    @JsonCreator
    public TravelNoticeRequest(@JsonProperty("destiny") String destiny) {
        this.destiny = destiny;
    }

    /**
     * jackson encountered a problem while deserializing
     * the json with the date in the parameter by the constructor,
     * for this reason it was necessary to create setter below
     * @param travelEndTime
     */
    public void setTravelEndTime(LocalDate travelEndTime) {
        this.travelEndTime = travelEndTime;
    }

    public LocalDate getTravelEndTime() {
        return travelEndTime;
    }

    public String getDestiny() {
        return destiny;
    }

    public TravelNotice toTravel(Card card, String ipClient, String userAgent) {
        return new TravelNotice(card, destiny, travelEndTime, ipClient, userAgent);
    }
}
