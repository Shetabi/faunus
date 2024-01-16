import React from "react";
// import * as Icon from "react-bootstrap-icons";

import "mdb-react-ui-kit/dist/css/mdb.min.css";
// import "@fortawesome/fontawesome-free/css/all.min.css";
import { MDBBadge, MDBIcon } from "mdb-react-ui-kit";

// import placeHolderImg from "../assets/images/placeholder.png";
import ficusLirata from '../../../assets/images/ficus_lirata.webp'
import waterPot from "../../../assets/images/green_small_watering_pot.jpg";
const Plant = ({ plant }) => {
  return (
    <>
      <div className="row mt-5 p-5"></div>
      <div className="row align-items-center">
        <div className="col w-25"></div>
        <div className="col w-50">
          <div className="card">
            <div className="card-header text-center">
              <h3>Plant Header</h3>
              <img
                className="card-img"
                height={400}
                src={ficusLirata}
                alt="placeholder"
              />
            </div>
            <div className="card-body">
              <div className="card-title">
                <span className="text-center">
                  <h3>{plant.name}</h3>
                </span>
              </div>
              <div className="card-group d-flex gap-5  justify-content-around">
                <MDBBadge pill color="success">
                  <MDBIcon far icon="sun" color="warning" size="2x" />
                  <span className="align-items-center">{plant.humidity}</span>
                </MDBBadge>
                <MDBBadge pill color="info">
                  <span>
                    <MDBIcon fas icon="lightbulb" color="warning" size="2x" />
                  </span>
                  <span className="align-items-center">{plant.lightness}</span>
                </MDBBadge>
              </div>
            </div>
            <div className="card-footer bg-body-secondary d-flex text-end">
                <p><b>مقدار آبیاری:</b> <small>{plant.watering}</small></p>
                <p className="img-fluid">
              <img
                  src={waterPot}
                  width={100}
                  className="bg-body-secondary img-thumbnail small"
                  alt="Water Pot"
                />
              </p>
            </div>
          </div>
        </div>
        <div className="col-4"></div>
      </div>
    </>
  );
};
export default Plant;
