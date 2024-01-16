import React, { useState, useEffect } from "react";

import "bootstrap/dist/css/bootstrap.min.css";

import "./Home.css";

import { displayPlants } from "../../../services/plant-service";
import placeHolderImage from "../../../assets/images/placeholder.png";
import Plant from "containers/public/plant/Plant";

const plantsListData = [
  {
    id: 1,
    name: "فیکوس لیراتا",
    humidity: "هوای خشک",
    lightness: "اتاق کم نور",
    watering: "با آب پاش به گلدان آنقدر آب بدهید تا آب از زیر گلدان خارج شود",
  },
  {
    id: 2,
    name: "Lady of the night",
    humidity: "",
    lightness: "",
    watering: "",
  },
  { id: 3, name: "Masdevallia", humidity: "", lightness: "", watering: "" },
  {
    id: 4,
    name: "Phalaenopsis bellina",
    humidity: "",
    lightness: "",
    watering: "",
  },
  { id: 5, name: "X Wilsonara", humidity: "", lightness: "", watering: "" },
  {
    id: 6,
    name: "Zygopetalum Maculatum",
    humidity: "",
    lightness: "",
    watering: "",
  },
  {
    id: 7,
    name: "Ground-rooting epidendrum",
    humidity: "",
    lightness: "",
    watering: "",
  },
  {
    id: 8,
    name: "Jewel Orchid 'Alba'",
    humidity: "",
    lightness: "",
    watering: "",
  },
];
const plant = plantsListData[0];

function Home() {
  const [plants, setPlants] = useState(plant);

  // const getPlants = ()=>{

  // }
  // useEffect(() => {
  //   displayPlants().then(
  //         data => {
  //           setPlants(data.data);
  //             console.log(JSON.stringify(data.data));
  //         }
  //     )
  //   }, []);

  return (
    <div className="container">
      <Plant plant={plant} />
    </div>
  );
}

export default Home;
