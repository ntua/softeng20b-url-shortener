import React from 'react';
import './App.css';
import NewForm from './NewForm';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { user: props.user };
  }

  render() {
    let userName = this.state.user === null ? "<anonymous>" : this.state.user;
    return (
      <div className="container">
        <p>User: {userName}</p>
        <NewForm user={this.state.user} />
      </div>
    );
  }
}

export default App;
