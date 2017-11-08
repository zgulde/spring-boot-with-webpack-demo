
fetch('/data')
  .then(response => response.json())
  .then(data => data.map(({name}) => name))
  .then(strings => strings.join(' '))
  .then(message => console.log(message))

