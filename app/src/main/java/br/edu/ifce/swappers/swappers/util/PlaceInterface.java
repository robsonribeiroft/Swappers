package br.edu.ifce.swappers.swappers.util;

import java.util.ArrayList;

import br.edu.ifce.swappers.swappers.model.Place;

/**
 * Created by Bolsista on 20/08/2015.
 */
public interface PlaceInterface {
    public void updatePlaceNear(ArrayList<Place> placeList);
    public void getDetailPlace(Place placeInformation);
}
