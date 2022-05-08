import React from "react";
import { useForm } from "react-hook-form";

import axios from "axios";
import { useState } from "react";

interface Row {
  StudentID: string;
  email: string;
  mealSwipes: string;
  flexPoints: string;
  bearBucks: string;
}

interface TransferMealProp {
  tableHeaders: string[] | null;
  rows: Row[] | null;
}

interface InputProp {
  name: string | null;
  description: string | null;
  amount: number | null;
}


function TransferMeal(props: TransferMealProp) {
  const [inputName, setInputName] = useState<string | null>(null);
  const [inputDescription, setInputDescription] = useState<string | null>(null);
  const [inputAmount, setInputAmount] = useState<string | null>(null);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<InputProp>();

  function storeInputData() {
    console.log(inputName);
    console.log(inputDescription);
    console.log(inputAmount);
  }

  const onSubmit = (inputData: InputProp) => storeInputData();

  const UpdateRequest = () => {
    console.log("headers: " + props.tableHeaders);
    console.log("rows: " + props.rows);

    storeInputData();

    // console.log("row 0: " + props.rows?.at(0)!.StudentID);
    // console.log("row 0: " + props.rows?.at(0)!.email);
    // console.log("row 0: " + props.rows?.at(0)!.mealSwipes);
    // console.log("row 0: " + props.rows?.at(0)!.flexPoints);
    // console.log("row 0: " + props.rows?.at(0)!.mealSwipes);

    const request = "http://localhost:4567/update"; // 1) location for request

    let config = {
      // 3) configuration
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    };

    axios
      .post(request, config)

      .then((response: any) => {
        console.log(response.data);
      })

      .catch((error: any) => {
        console.log(error);
      });
  };

  return (
    <React.Fragment>
      <div className="page-info">
        <h1>Transfer: Meal Credits</h1>
      </div>

      <div className={"page-buttons"}>
        <form onSubmit={handleSubmit(UpdateRequest)}>
          <div className="input-group">
            <div>To Who*:</div>

            <input
              type="text"
              placeholder="Name"
              {...register("name", {
                required: true,
                maxLength: 20,
                pattern: /^[A-Za-z]+$/i,
              })}
              onChange={(event: React.ChangeEvent<HTMLInputElement>): void =>
                setInputName(event.target.value)
              }
            />
            <div className="error-message">{errors.name && "Required!"}</div>
          </div>

          <div className="input-group">
            <div>Description:</div>

            <input
              type="text"
              placeholder="Optional"
              {...register("description", { maxLength: 100 })}
              onChange={(event: React.ChangeEvent<HTMLInputElement>): void =>
                setInputDescription(event.target.value)
              }
            />
          </div>

          <div className="input-group">
            <div>Amount*:</div>

            <input
              type="number"
              placeholder="Between 1-20"
              {...register("amount", { required: true, min: 1, max: 20 })}
              onChange={(event: React.ChangeEvent<HTMLInputElement>): void =>
                setInputAmount(event.target.value)
              }
            />
            <div className="error-message">{errors.amount && "Required!"}</div>
          </div>

          <div className="input-group">
            <button className="sending-button" type="submit">
              Transfer!
            </button>
          </div>
        </form>
      </div>
    </React.Fragment>
  );

}

export default TransferMeal;
