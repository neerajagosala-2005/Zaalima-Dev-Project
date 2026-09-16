import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [products, setProducts] = useState([])
  const [cart, setCart] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  // Get products from Product Service through API Gateway
  useEffect(() => {
    fetch('http://localhost:8080/product-service/api/products')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to fetch products')
        }

        return response.json()
      })
      .then((data) => {
        setProducts(data)
        setLoading(false)
      })
      .catch((error) => {
        console.error(error)
        setError('Unable to load products')
        setLoading(false)
      })
  }, [])

  // Add product to cart
  const addToCart = (product) => {
    setCart([...cart, product])
    alert(`${product.name} added to cart!`)
  }

  // Remove product from cart
  const removeFromCart = (index) => {
    setCart(cart.filter((_, i) => i !== index))
  }

  // Buy Now
  const buyNow = (product) => {
    fetch('http://localhost:8080/order-service/orders', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        userId: 1,
        productId: product.id,
        quantity: 1,
        totalPrice: product.price,
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Order creation failed')
        }

        return response.json()
      })
      .then((data) => {
        alert(`Order created successfully! Order ID: ${data.id}`)
      })
      .catch((error) => {
        console.error(error)
        alert('Order creation failed')
      })
  }

  // Calculate cart total
  const cartTotal = cart.reduce(
    (total, item) => total + Number(item.price),
    0
  )

  return (
    <div>
      {/* Header */}
      <header>
        <h1>My E-Commerce Store</h1>
        <p>Welcome to our online store</p>

        <div className="cart">
          🛒 Cart: {cart.length}
        </div>
      </header>

      {/* Cart Section */}
      {cart.length > 0 && (
        <section className="cart-section">
          <h2>Cart Items</h2>

          {cart.map((item, index) => (
            <div className="cart-item" key={index}>
              <div>
                <h3>{item.name}</h3>
                <p>Price: ₹{item.price}</p>
              </div>

              <button onClick={() => removeFromCart(index)}>
                Remove
              </button>
            </div>
          ))}

          <h3>Cart Total: ₹{cartTotal}</h3>
        </section>
      )}

      {/* Products */}
      <main>
        <h2>Products</h2>

        {loading && <p>Loading products...</p>}

        {error && <p>{error}</p>}

        {!loading && !error && products.length === 0 && (
          <p>No products available.</p>
        )}

        <div className="products">
          {products.map((product) => (
            <div className="product-card" key={product.id}>
              <h3>{product.name}</h3>

              {product.description && (
                <p>{product.description}</p>
              )}

              <p>₹{product.price}</p>

              <p>Quantity: {product.quantity}</p>

              <button onClick={() => addToCart(product)}>
                Add to Cart
              </button>

              <button onClick={() => buyNow(product)}>
                Buy Now
              </button>
            </div>
          ))}
        </div>
      </main>
    </div>
  )
}

export default App