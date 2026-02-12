const maringaLat = -23.4209995;
    const maringaLng = -51.9330558;

    const map = L.map('map').setView([maringaLat, maringaLng], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    const pontos = [
      { nome: "Ponto A", lat: maringaLat, lng: maringaLng, descricao: "Coleta de eletrônicos" },
      { nome: "Ponto B", lat: maringaLat + 0.01, lng: maringaLng + 0.01, descricao: "Coleta de óleo usado" }
    ];

    pontos.forEach(ponto => {
      L.marker([ponto.lat, ponto.lng])
        .addTo(map)
        .bindPopup(`<strong>${ponto.nome}</strong><br>${ponto.descricao}`);
    });