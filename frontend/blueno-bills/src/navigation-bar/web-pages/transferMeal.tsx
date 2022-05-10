import React from "react";
import { useForm } from "react-hook-form";
import { useState } from "react";

// represents a row of data
interface Row {
  StudentID: string;
  email: string;
  mealSwipes: string;
  flexPoints: string;
  bearBucks: string;
}

// represents parameters for transfers
interface TransferMealProp {
  tableHeaders: string[] | null;
  rows: Row[] | null;
  userEmail: string;
  userMS: number;
}

function TransferMeal(props: TransferMealProp) {
  // initialize input data
  const [inputEmail, setInputEmail] = useState<string>("");
  const [inputDescription, setInputDescription] = useState<string | null>(null);
  const [inputAmount, setInputAmount] = useState<string | null>(null);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  let recipientCurrentMeal: number = 0; // amount of meal swipes of the person receiving

  if (props.rows) {
    // check if there's rows
    for (let i = 0; i < props.rows.length; i++) {
      if (equalsIgnoringCase(props.rows[i].email, inputEmail)) {
        recipientCurrentMeal = parseInt(props.rows[i].mealSwipes);
      }
    }
  }

  // https://stackoverflow.com/questions/2140627/how-to-do-case-insensitive-string-comparison
  function equalsIgnoringCase(text: string, other: string) {
    return text.localeCompare(other, undefined, { sensitivity: "base" }) === 0;
  }

  let userTransferMeal: number = 0; // amount of meal swipes of the person transferring
  let userReceiveMeal: number = 0; // amount of meal swipes of the person receiving

  if (inputAmount) {
    userTransferMeal = props.userMS - parseInt(inputAmount);
    userReceiveMeal = recipientCurrentMeal + parseInt(inputAmount);
  }

  // updating the "mealSwipes" column in the StudentData table and
  // decrementing the current user's meal swipes since they are transferring
  const UpdateRequest = async () => {
    console.log("User Who Transferred Meal: " + userTransferMeal);
    console.log("User Who Received Meal: " + userReceiveMeal);
    console.log("Input Email: " + inputEmail);
    console.log("Input Description: " + inputDescription);
    console.log("Input Amount: " + inputAmount);

    const transferBody: string =
      '{"tableName": ' +
      '"' +
      "Students" +
      '"' +
      ', "colNameToNewVal" : {' +
      '"' +
      "mealSwipes" +
      '"' +
      " : " +
      '"' +
      userTransferMeal +
      '"' +
      '}, "conditions" : {' +
      '"' +
      "email" +
      '"' +
      " : " +
      '"' +
      props.userEmail +
      '"' +
      "}}";

    const requestTransferOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: transferBody,
    };

    const responseOne = await fetch(
      "http://localhost:4567/update",
      requestTransferOptions
    );

    const jsonResponseOne = await responseOne.json();
    console.log("Response 1: " + jsonResponseOne);

    // another POST request to update the values of the user who was sent the meal swipe
    const recipientBody: string =
      '{"tableName": ' +
      '"' +
      "Students" +
      '"' +
      ', "colNameToNewVal" : {' +
      '"' +
      "mealSwipes" +
      '"' +
      " : " +
      '"' +
      userReceiveMeal +
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

    const requestRecipientOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: recipientBody,
    };

    const responseTwo = await fetch(
      "http://localhost:4567/update",
      requestRecipientOptions
    );

    const jsonResponseTwo = await responseTwo.json();
    console.log("Response 2: " + jsonResponseTwo);

    fetch("http://localhost:4567/update", requestTransferOptions)
      .then((response) => {
        response.json();
      })
      .then(() => (window.location.href = "/transferMeal"));
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
