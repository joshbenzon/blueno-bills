import React from "react";
import { useForm } from "react-hook-form";
import { useState } from "react";

interface Row {
  StudentID: string;
  email: string;
  mealSwipes: string;
  flexPoints: string;
  bearBucks: string;
}

interface TableProp {
  tableHeaders: string[] | null;
  rows: Row[] | null;
}

function RequestFlex(props: TableProp) {
  // initialize input data
  const [inputEmail, setInputEmail] = useState<string>("");
  const [inputDescription, setInputDescription] = useState<string | null>(null);
  const [inputAmount, setInputAmount] = useState<string | null>(null);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data: any) => console.log(data);

  return (
    <React.Fragment>
      <div className="page-info">
        <h1>Request: Flex Points</h1>
      </div>

      <div className={"page-buttons"}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className="input-group">
            <div>From Who*:</div>

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
              {...register("amount", { required: true, min: 1, max: 100 })}
              onChange={(event: React.ChangeEvent<HTMLInputElement>): void =>
                setInputAmount(event.target.value)
              }
            />
            <div className="error-message">{errors.amount && "Required!"}</div>
          </div>

          <div className="input-group">
            <button className="sending-button" type="submit">
              Request
            </button>
          </div>
        </form>
      </div>
    </React.Fragment>
  );
}

export default RequestFlex;
