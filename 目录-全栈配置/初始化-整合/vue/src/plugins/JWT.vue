<script>
	import jwt from 'jsonwebtoken'
	export default {
		mounted() {
			let data = "jwt"
			let str = jwt.decode(data)

		}
	}
</script>
