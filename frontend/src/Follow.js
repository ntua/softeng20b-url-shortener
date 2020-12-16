import React from 'react';
import { followShortUrl } from "./api";

class Follow extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      shortUrl: props.id,
      targetUrl: null,
      error: null
    };
  }

  componentDidMount() {
    followShortUrl(this.state.shortUrl)
      .then(json => {
        console.log(json);
        setTimeout(() => {
          this.setState({ targetUrl: json.data.target, error: null });
        }, 0);
      })
      .catch(err => {
        console.log(err);
        this.setState({ targetUrl: null, error: err });
      });
  }

  render() {
    if (this.state.targetUrl !== null)
      window.location.href = this.state.targetUrl;
    else if (this.state.error !== null)
      return <p>Not found!</p>;
    return null;
  }
}

export default Follow;
