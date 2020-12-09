import React from "react";
import "./NewForm.css";
import { generateShortUrl } from "./api";

class NewForm extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      originalUrl: "",
      shortUrl: null,
      error: null,
    };
    this.user = 1;  // user id for anonymous
    this.handleUserInput = this.handleUserInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleUserInput(e) {
    const name = e.target.name;
    const value = e.target.value;
    this.setState({ [name]: value });
  }

  handleSubmit() {
    this.setState({ error: null });
    if (this.state.originalUrl) {
      this.setState({ shortUrl: null });
      let reqObj = {
        target: this.state.originalUrl,
        user: { id: this.user }
      };
      generateShortUrl(reqObj)
        .then(json => {
          setTimeout(() => {
            this.setState({ shortUrl: json.data.id });
          }, 0);
        })
        .catch(err => {
          this.setState({ error: err.error })
        });
    } else {
      this.setState({ error: "Original URL is required" });
    }
  }

  render() {
    return (
      <div className="new-form">
        <h1>URL Shortener</h1>
        <input
          name="originalUrl"
          field="originalUrl"
          placeholder="Paste your link.."
          value={this.state.originalUrl}
          onChange={this.handleUserInput}
        />
        <button
          className="btn waves-effect waves-light submit-btn"
          name="action"
          onClick={this.handleSubmit}
        >
          Generate!
        </button>
        {this.state.error !== null && (
          <div className="error">{this.state.error}</div>
        )}
        {this.state.shortUrl !== null && (
          <div className="shorten-title">
            Short URL is:{` `}
            <a target="_new" href={this.state.shortUrl}>
              {this.state.shortUrl}
            </a>
          </div>
        )}
      </div>
    );
  }
}

export default NewForm;
