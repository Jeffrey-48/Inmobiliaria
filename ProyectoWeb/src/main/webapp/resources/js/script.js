function onLoadIndex() {
	if (map_mapa != null) {
		map_mapa.on("locationfound", onLocationFound);
		map_mapa.on("locationerror", onLocationError);
		map_mapa.locate({
			setView : true,
			maxZoom : 16
		});
	}
}

function onLoadBody() {
	map_mapa.on("locationfound", onLocationFound);
	map_mapa.on("locationerror", onLocationError);
	map_mapa.on('click', onClickMarker);
	map_mapa.locate({
		setView : true,
		maxZoom : 16
	});
}

function onLocationFound(e) {
	var radio = e.accuracy / 2
	L.marker(e.latlng, {
		icon : icon
	}).addTo(map_mapa).bindPopup(
			"Usted se encuentra a " + radio + "metros de este punto")
	L.circle(e.latlng, radio).addTo(map_mapa)
}
function onLocationError(e) {
	alert(e.message)
}

function onClickMarker(e) {
	layer.clearLayers();
	var nuevoMarker = new L.marker(e.latlng, {
		icon : icon
	}).addTo(layer);
	funcionRemota([ {
		name : "lat",
		value : e.latlng.lat
	}, {
		name : "lng",
		value : e.latlng.lng
	} ]);
}