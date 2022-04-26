import React from 'react';
import Button from "../../buttons/components/buttons";
import {useForm} from "react-hook-form";

const TransferMeal = () => {
    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const onSubmit = (data: any) => console.log(data);

    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'Center',
                alignItems: 'Top',
                height: '100vh'
            }}
        >
            <div className="request-info"
                 style={{
                     display: 'flex',
                     padding: '10px'
                 }}>

                <h1>Transfer: Meal Credits</h1>
            </div>

            <form onSubmit={handleSubmit(onSubmit)}

                  style={{
                      display: 'flex',
                      padding: '10px'
                  }}>

                <div>
                    To Who:
                    <input {...register("name",
                        {required: true, maxLength: 20, pattern: /^[A-Za-z]+$/i})} />
                    {errors.name && "To Who is Required!"}
                </div>

                <div>
                    Description:
                    <input {...register("description", {maxLength: 100})} />
                </div>

                <div>
                    Amount:
                    <input type="number" {...register("amount", {min: 0, max: 20})} />
                    {errors.amount && "Amount Must Be in Bounds!"}
                </div>

                <div>
                    <input type="submit"/>
                </div>
            </form>

            <div className="transfer-meal-button"
                 style={{
                     display: 'flex',
                     padding: '10px'
                 }}>

                <Button
                    border="none"
                    color="#4CC37B"
                    height="100px"
                    width="200px"
                    radius="5%"
                    children="Transfer"
                    onClick={() => console.log("Transfer Meal Credit!")}
                />
            </div>
        </div>
    );
};

export default TransferMeal;
