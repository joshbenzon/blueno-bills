import React from 'react';
import Button from "../../buttons/components/buttons";
import {useForm} from "react-hook-form";

const TransferMeal = () => {
    const {register, handleSubmit, formState: {errors}} = useForm();
    const onSubmit = (data: any) => console.log(data);  // stores in map

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
                    {errors.name && "Required!"}
                </div>

                <div>
                    Description:
                    <input {...register("description", {maxLength: 100})} />
                </div>

                <div>
                    Amount:
                    <input type="number" {...register("amount", {required: true, min: 0, max: 20})} />
                    {errors.amount && "Required and Must Be in Bounds!"}
                </div>

                <div>
                    <input className="transfer-meal-button" type="submit"
                           style={{
                               display: 'flex',
                               padding: '10px',
                               border: "none",
                               background: "#4CC37B",
                               height: "100px",
                               width: "200px",
                               borderRadius: "40px",
                               textAlign: "center",
                               boxShadow: "0 6px 20px -5px",
                               cursor: "pointer",
                               fontSize: "22px",
                           }}/>
                </div>
            </form>

            {/*<div className="transfer-meal-button"*/}
            {/*     style={{*/}
            {/*         display: 'flex',*/}
            {/*         padding: '10px'*/}
            {/*     }}>*/}

            {/*    <Button*/}
            {/*        border="none"*/}
            {/*        color="#4CC37B"*/}
            {/*        height="100px"*/}
            {/*        width="200px"*/}
            {/*        radius="5%"*/}
            {/*        children="Transfer"*/}
            {/*        onClick={() => console.log("Transfer Meal Credit!")}*/}
            {/*    />*/}
            {/*</div>*/}
        </div>
    );
};

export default TransferMeal;
