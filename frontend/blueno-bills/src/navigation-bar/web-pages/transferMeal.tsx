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
  email: string | null;
  description: string | null;
  amount: number | null;
}

function TransferMeal(props: TransferMealProp) {
  const onSubmit = (inputData: InputProp) => storeInputData();

  function storeInputData() {
    console.log(inputEmail);
    console.log(inputDescription);
    console.log(inputAmount);
  }

  const [inputEmail, setInputEmail] = useState<string>("");
  const [inputDescription, setInputDescription] = useState<string | null>(null);
  const [inputAmount, setInputAmount] = useState<string | null>(null);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<InputProp>();

  const currUserEmail = "Jillian_Dominguez@brown.edu"; //hard coding for now until we have auth established and can store curr user data
  let currUserMealSwipes: number = 0;
  let recipientCurrMealSwipes: number = 0; //the current number of meal swipes of the person being sent swipes

  if (props.rows) {
    for (let i = 0; i < props.rows.length; i++) {
      if (equalsIgnoringCase(props.rows[i].email, currUserEmail)) {
        //user

        currUserMealSwipes = parseInt(props.rows[i].mealSwipes);
      }

      if (equalsIgnoringCase(props.rows[i].email, inputEmail)) {
        //recipient

        recipientCurrMealSwipes = parseInt(props.rows[i].mealSwipes);
      }
    }
  }

  //from here: https://stackoverflow.com/questions/2140627/how-to-do-case-insensitive-string-comparison
  function equalsIgnoringCase(text: string, other: string) {
    return text.localeCompare(other, undefined, { sensitivity: "base" }) === 0;
  }

  let newNumMealSwipes: number = 0;
  let newRecipientMealSwipes: number = 0;

  if (inputAmount) {
    newNumMealSwipes = currUserMealSwipes - parseInt(inputAmount);
    newRecipientMealSwipes = recipientCurrMealSwipes + parseInt(inputAmount);

    console.log("new user meal swipes: " + newNumMealSwipes);
    console.log("recipient new meal swipes: " + newRecipientMealSwipes);
  }

  const UpdateRequest = () => {
    storeInputData();
    //here we are updating the mealSwipes column in the StudentData table
    //decrementing the current user's meal swipes by 1 since they are transferring

    const body: string =
      '{"tableName": ' +
      '"' +
      "StudentData" +
      '"' +
      ', "colNameToNewVal" : {' +
      '"' +
      "mealSwipes" +
      '"' +
      " : " +
      '"' +
      newNumMealSwipes +
      '"' +
      '}, "conditions" : {' +
      '"' +
      "email" +
      '"' +
      " : " +
      '"' +
      currUserEmail +
      '"' +
      "}}";

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: body,
    };

    console.log("req body: " + requestOptions.body);

    fetch("http://localhost:4567/update", requestOptions).then((response) => {
      response.json();
      console.log("response: " + response.status);
    });

    //we need another POST request to update the values of the user who was sent the meal swipe

    const recipientBody: string =
      '{"tableName": ' +
      '"' +
      "StudentData" +
      '"' +
      ', "colNameToNewVal" : {' +
      '"' +
      "mealSwipes" +
      '"' +
      " : " +
      '"' +
      newRecipientMealSwipes +
      '"' +
      '}, "conditions" : {' +
      '"' +
      "email" +
      '"' +
      " : " +
      '"' +
      inputEmail +
      '"' +
      "}}";

    const requestOptionsRecipient = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: body,
    };

    console.log("req body: " + requestOptions.body);

    fetch("http://localhost:4567/update", requestOptions).then((response) => {
      response.json();
      console.log("response: " + response.status);
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
              placeholder="Email"
              {...register("email", {
                required: true,
                maxLength: 50,
              })}
              onChange={(event: React.ChangeEvent<HTMLInputElement>): void =>
                setInputEmail(event.target.value)
              }
            />
            <div className="error-message">{errors.email && "Required!"}</div>
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
