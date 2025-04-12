// Form validation
(function () {
    'use strict'

    // Fetch all forms that need validation
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
})()

// Phone number formatting
document.addEventListener('DOMContentLoaded', function() {
    const phoneInputs = document.querySelectorAll('input[type="tel"]')
    
    phoneInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '')
            if (value.length > 0) {
                if (value.length <= 3) {
                    value = value
                } else if (value.length <= 6) {
                    value = value.slice(0, 3) + '-' + value.slice(3)
                } else {
                    value = value.slice(0, 3) + '-' + value.slice(3, 6) + '-' + value.slice(6, 10)
                }
            }
            e.target.value = value
        })
    })
})

// Salary formatting
document.addEventListener('DOMContentLoaded', function() {
    const salaryInput = document.getElementById('salary')
    if (salaryInput) {
        salaryInput.addEventListener('input', function(e) {
            let value = e.target.value
            if (value) {
                value = parseFloat(value).toFixed(2)
                if (!isNaN(value)) {
                    e.target.value = value
                }
            }
        })
    }
})

// Auto-capitalize names
document.addEventListener('DOMContentLoaded', function() {
    const nameInputs = document.querySelectorAll('#firstName, #lastName')
    
    nameInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            let value = e.target.value
            if (value) {
                e.target.value = value.charAt(0).toUpperCase() + value.slice(1)
            }
        })
    })
})

// Confirmation dialogs
document.addEventListener('DOMContentLoaded', function() {
    const deleteLinks = document.querySelectorAll('a[href*="/delete/"]')
    
    deleteLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to delete this employee?')) {
                e.preventDefault()
            }
        })
    })
})

// Auto-dismiss alerts
document.addEventListener('DOMContentLoaded', function() {
    const alerts = document.querySelectorAll('.alert:not(.alert-permanent)')
    
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'opacity 0.5s ease-in-out'
            alert.style.opacity = '0'
            setTimeout(() => alert.remove(), 500)
        }, 5000)
    })
}) 