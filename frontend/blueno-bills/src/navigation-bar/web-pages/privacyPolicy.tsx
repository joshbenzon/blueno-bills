import React from "react";

function PrivacyPolicy() {
  const navigateToHome = () => {
    window.location.href = "/home";
  };

  const navigateToLogin = () => {
    window.location.href = "/";
  };

  return (
    <React.Fragment>
      <div className="title-page">
        <div>
          <img
            className="login-pic"
            src={require("../images/blueno-logo.png")}
            alt="blueno bills logo"
          />
        </div>

        <div className="page-info">
          <h1 className="privacy-title">Warning!</h1>
        </div>

        <div className="page-info">
          <p className="privacy-description">
            By continuing to "Blueno Bills," you agree to displaying some
            personal information, such as your email, your amount of meal
            swipes, your amount of flex points, and your amount of bear bucks to
            other users. You also indicate that you have read and agreed to our
            terms and conditions. If you don't feel comfortable sharing this
            information (or disagree), please do not continue any further. Thank
            you!
          </p>
        </div>

        <div className={"page-buttons"}>
          <div>
            <button className="continue-button" onClick={navigateToHome}>
              Continue
            </button>
          </div>
        </div>

        <div className={"page-buttons"}>
          <div>
            <button className="go-back-button" onClick={navigateToLogin}>
              Go Back
            </button>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
}

export default PrivacyPolicy;
