import { Admin, Resource, ListGuesser, List, Datagrid, TextField, NumberField, ImageField } from "react-admin";
import jsonServerProvider from "ra-data-json-server";

// Read REACT_APP_API_URL from environment variable
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

const dataProvider = jsonServerProvider(REACT_APP_API_URL);

const ItemList = () => (
  <List>
      <Datagrid>
          <TextField source="id" />
          <TextField source="sku" label="SKU" />
          <TextField source="description" />
          <NumberField source="qty" label="Qty" />
          <ImageField source="imageUrl" label="Image URL" />
      </Datagrid>
  </List>
);

const App = () => (
  <Admin dataProvider={dataProvider}>
    <Resource name="items" options={{ label: 'Inventory' }} list={ItemList} />
    <Resource name="orders" options={{ label: 'Accounting' }} list={ListGuesser} />
    <Resource name="shippings" list={ListGuesser} />
  </Admin>
);

export default App;