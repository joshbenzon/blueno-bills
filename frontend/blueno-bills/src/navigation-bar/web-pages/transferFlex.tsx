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
interface TransferFlexProp {
  tableHeaders: string[] | null;
  rows: Row[] | null;
  userEmail: string;
  userFP: number;
}

function TransferFlex(props: TransferFlexProp) {
  // initialize input data
  const [inputEmail, setInputEmail] = useState<string>("");
  const [inputDescription, setInputDescription] = useState<string | null>(null);
  const [inputAmount, setInputAmount] = useState<string | null>(null);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  let recipientCurrentFlex: number = 0; // amount of flex points of the person receiving

  if (props.rows) {
    // check if there's rows
    for (let i = 0; i < props.rows.length; i++) {
      if (equalsIgnoringCase(props.rows[i].email, inputEmail)) {
        recipientCurrentFlex = parseInt(props.rows[i].flexPoints);
      }
    }
  }

  // https://stackoverflow.com/questions/2140627/how-to-do-case-insensitive-string-comparison
  function equalsIgnoringCase(text: string, other: string) {
    return text.localeCompare(other, undefined, { sensitivity: "base" }) === 0;
  }

  let userTransferFlex: number = 0; // amount of flex points of the person transferring
  let userReceiveFlex: number = 0; // amount of flex points of the person receiving

  if (inputAmount) {
    userTransferFlex = props.userFP - parseInt(inputAmount);
    userReceiveFlex = recipientCurrentFlex + parseInt(inputAmount);
  }

  // updating the "flexPoints" column in the StudentData table and
  // decrementing the current user's flex points since they are transferring
  const UpdateRequest = async () => {
    console.log("User Who Transferred Flex: " + userTransferFlex);
    console.log("User Who Received Flex: " + userReceiveFlex);
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
      "flexPoints" +
      '"' +
      " : " +
      '"' +
      userTransferFlex +
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

    // another POST request to update the values of the user who was sent the flex points
    const recipientBody: string =
      '{"tableName": ' +
      '"' +
      "Students" +
      '"' +
      ', "colNameToNewVal" : {' +
      '"' +
      "flexPoints" +
      '"' +
      " : " +
      '"' +
      userReceiveFlex +
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
      .then(() => (window.location.href = "/transferFlex"));
  };

  return (
    <React.Fragment>
      <div className="page-info">
        <h1>Transfer: Flex Points</h1>
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
              placeholder="Between 1-100"
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

export default TransferFlex;
